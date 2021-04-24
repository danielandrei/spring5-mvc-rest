package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface RestControllerTest {

  default String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
