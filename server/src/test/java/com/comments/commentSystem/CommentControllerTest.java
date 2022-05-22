
package com.comments.commentSystem;

import com.comments.commentSystem.controller.CommentController;
import com.comments.commentSystem.model.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest
@WithMockUser
@ContextConfiguration(classes = {CommentRestControllerTest.class})
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentController commentController;

    Comment mockCommit = new Comment(1L, "Naming", "Commenting", 222L);

    /*Test case for UI Design*/
    @Test
    public void createCommentMock() throws Exception {

        String sampleMockJson = "{\n" +
                "            \"id\":1,\n" +
                "                \"name\":\"Vinod\",\n" +
                "                \"comment\":\"checkingData\"\n" +
                "\n" +
                "        }";
        Mockito.when(commentController.createComment(Mockito.any(Comment.class))).thenReturn(mockCommit);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/post/comments")
                .accept(MediaType.APPLICATION_JSON).content(sampleMockJson)
                .contentType(MediaType.APPLICATION_JSON);
        String nameMock = mockCommit.getName();

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
      //  System.out.println("response::" + response.);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(nameMock, "Naming");
    }


/*Test cases for Async Operation Controller*/


    @Test
    public void createCommentAsyncMock() throws Exception {

        String sampleMockJson = "{\n" +
                "            \"id\":1,\n" +
                "                \"name\":\"Vinod\",\n" +
                "                \"comment\":\"checkingData\"\n" +
                "\n" +
                "        }";
        Mockito.when(commentController.createComment(Mockito.any(Comment.class))).thenReturn(mockCommit);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/post/comments/Async")
                .accept(MediaType.APPLICATION_JSON).content(sampleMockJson)
                .contentType(MediaType.APPLICATION_JSON);
        String nameMock = mockCommit.getName();

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //  System.out.println("response::" + response.);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(nameMock, "Naming");
    }

    @Test
    public void retrieveDetailsForComment() throws Exception {

            Mockito.when(
                commentController.getAllComments()).thenReturn(Collections.singletonList(mockCommit));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/get/comments").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "[{\"id\":1,\"name\":\"Naming\",\"content\":\"Commenting\",\"parentId\":222}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void retrieveDetailsByMockId() throws Exception {

        Mockito.when(
                commentController.getCommentById(Mockito.any())).thenReturn(mockCommit);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/comments/1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println("result::"+result.getResponse());
        String expected = "{\"id\":1,\"name\":\"Naming\",\"content\":\"Commenting\",\"parentId\":222}";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


}
