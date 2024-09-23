package com.baranbatur.newMotherHelper.service;

import com.baranbatur.newMotherHelper.converter.GenericConverter;
import com.baranbatur.newMotherHelper.dto.requests.BlogPostRequest;
import com.baranbatur.newMotherHelper.dto.response.BlogPostResponse;
import com.baranbatur.newMotherHelper.dto.response.CommentResponse;
import com.baranbatur.newMotherHelper.dto.response.UserResponse;
import com.baranbatur.newMotherHelper.exception.NotFoundException;
import com.baranbatur.newMotherHelper.model.BlogPost;
import com.baranbatur.newMotherHelper.model.Comment;
import com.baranbatur.newMotherHelper.model.User;
import com.baranbatur.newMotherHelper.model.Vote;
import com.baranbatur.newMotherHelper.repository.BlogPostRepo;
import com.baranbatur.newMotherHelper.repository.VoteRepo;
import com.baranbatur.newMotherHelper.service.abstracts.BlogPostService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {

    private BlogPostRepo blogPostRepo;

    private VoteRepo voteRepo;

    private final GenericConverter<BlogPost, BlogPostRequest> blogPostRequestGenericConverter;
    private final GenericConverter<BlogPost, BlogPostResponse> blogPostResponseGenericConverter;
    private GenericConverter<Comment, CommentResponse> commentResponseConverter = null;


    public BlogPostServiceImpl(BlogPostRepo blogPostRepo, VoteRepo voteRepo) {
        this.blogPostRepo = blogPostRepo;
        this.voteRepo = voteRepo;
        this.blogPostRequestGenericConverter = new GenericConverter<>(BlogPostRequest -> new BlogPostRequest(BlogPostRequest.getTitle(), BlogPostRequest.getContent(), BlogPostRequest.getUser()), BlogPostRequest -> {
            BlogPost blogPost = new BlogPost();
            blogPost.setTitle(BlogPostRequest.getTitle());
            blogPost.setContent(BlogPostRequest.getContent());
            blogPost.setUser(BlogPostRequest.getUser());
            blogPost.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            blogPost.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            return blogPost;
        });

        this.blogPostResponseGenericConverter = new GenericConverter<>(blogPost -> {
            BlogPostResponse response = new BlogPostResponse();
            response.setId(blogPost.getId());
            response.setTitle(blogPost.getTitle());
            response.setContent(blogPost.getContent());
            response.setVoteCount(blogPost.getVoteCount());
            response.setCommentCount(blogPost.getComments().size());
            response.setCreatedAt(blogPost.getCreatedAt());
            response.setUpdatedAt(blogPost.getUpdatedAt());
            UserResponse userResponse = new UserResponse();
            if (blogPost.getUser() != null) {
                userResponse.setId(blogPost.getUser().getId());
                userResponse.setName(blogPost.getUser().getName());
                userResponse.setSurname(blogPost.getUser().getSurname());
                userResponse.setEmail(blogPost.getUser().getEmail());
                userResponse.setGender(blogPost.getUser().getGender().name());
                userResponse.setRole(blogPost.getUser().getRole().name());
            }
            response.setUser(userResponse);
            List<CommentResponse> commentResponses = blogPost.getComments()
                    .stream()
                    .map(commentResponseConverter::convertToDto)
                    .collect(Collectors.toList());
            response.setComments(commentResponses);
            return response;
        }, blogPostResponse -> {
            BlogPost blogPost = new BlogPost();
            blogPost.setId(blogPostResponse.getId());
            blogPost.setTitle(blogPostResponse.getTitle());
            blogPost.setContent(blogPostResponse.getContent());
            blogPost.setVoteCount(blogPostResponse.getVoteCount());
            blogPost.setCreatedAt(blogPostResponse.getCreatedAt());
            blogPost.setUpdatedAt(blogPostResponse.getUpdatedAt());
            User user = new User();
            user.setId(blogPostResponse.getUser().getId());
            blogPost.setUser(user);
            return blogPost;
        });
        this.commentResponseConverter = new GenericConverter<>(
                comment -> {
                    CommentResponse response = new CommentResponse();
                    response.setId(comment.getId());
                    response.setContent(comment.getContent());
                    response.setCreatedAt(comment.getCreatedAt());
                    response.setUpdatedAt(comment.getUpdatedAt());

                    UserResponse userResponse = new UserResponse();
                    User user = comment.getUser();
                    if (user != null) {
                        userResponse.setId(user.getId());
                        userResponse.setName(user.getName());
                        userResponse.setSurname(user.getSurname());
                        userResponse.setEmail(user.getEmail());
                        userResponse.setGender(user.getGender().name());
                        userResponse.setRole(user.getRole().name());
                    }
                    response.setUser(userResponse);
                    return response;
                },
                commentResponse -> {
                    // Implement if needed
                    return null;
                });
    }

    @Override
    public List<BlogPostResponse> getAllBlogPosts() {
        List<BlogPost> blogPosts = blogPostRepo.findAllOrderByVoteCountDescCreatedAtDesc();
        if (blogPosts.isEmpty()) {
            throw new NotFoundException("No blog posts found");
        }
        return blogPosts.stream().map(blogPostResponseGenericConverter::convertToDto).toList();
    }

    @Override
    public BlogPostResponse createBlogPost(BlogPostRequest blogPost) {
        BlogPost blogPost1 = blogPostRequestGenericConverter.convertToEntity(blogPost);
        return blogPostResponseGenericConverter.convertToDto(blogPostRepo.save(blogPost1));
    }

    @Override
    @Transactional
    public void votePost(Integer postId, Integer userId, Boolean voteType) {
        Optional<Vote> existingVote = voteRepo.findByPost_IdAndUser_Id(postId, userId);
        BlogPost post = blogPostRepo.findById(postId).orElseThrow(() -> new NotFoundException("Post not found"));

        if (existingVote.isPresent()) {
            Vote vote = existingVote.get();
            if (vote.getVoteType() != voteType) {
                vote.setVoteType(voteType);
                voteRepo.save(vote);
                post.setVoteCount(post.getVoteCount() + (voteType ? 1 : -1));
                blogPostRepo.save(post);
            }
        } else {
            Vote vote = new Vote();
            vote.setPost(post);
            User user = new User();
            user.setId(userId);
            vote.setUser(user);
            vote.setVoteType(voteType);
            voteRepo.save(vote);
            post.setVoteCount(post.getVoteCount() + (voteType ? 1 : -1));
            blogPostRepo.save(post);
        }
    }
}
