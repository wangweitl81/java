package com.lxtech.novel;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NovelControllerTest{
	@LocalServerPort
	private int port;
	
	@Autowired
    private MockMvc mvc;
    
	private URL base;
	
	@Autowired
	private TestRestTemplate template;
	
	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}
	
    @Test
    public void testInvalidParameters() throws Exception{
    	ResponseEntity<Map> response = template.getForEntity(base.toString(), Map.class);
    	Map responseMap = response.getBody();
    	Assert.assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatusCodeValue());
    }
}