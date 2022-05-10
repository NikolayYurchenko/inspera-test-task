package com.inspera.interview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inspera.interview.util.Parser;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InterviewApplicationTests {

    @Autowired
    private ObjectMapper mapper;
    private JSONObject before;
    private JSONObject after;
    private JSONObject diff;

    /**
     * Read prepared JSON files
     * @throws IOException
     */
    @BeforeEach
    public void setUp() throws IOException {

        before = mapper.readValue(new File("src/test/resources/before.json"), JSONObject.class);
        after = mapper.readValue(new File("src/test/resources/after.json"), JSONObject.class);
        diff = mapper.readValue(new File("src/test/resources/diff.json"), JSONObject.class);
    }

    @Test
    public void testParseDifferenceBetweenJsonObjects() {

        Parser parser = new Parser(mapper);

        JSONObject parsed = parser.parse(before, after);

        assertEquals(diff, parsed);
    }
}
