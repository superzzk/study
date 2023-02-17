package jacksonStudy.custom_serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jacksonStudy.enums.Distance;
import org.junit.Test;

/**
 * @author zhangzhongkun
 * @since 2019-11-08 16:59
 **/
public class Demo1 {
    @Test
    public void default_serialize () throws JsonProcessingException {

        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        String serialized = new ObjectMapper().writeValueAsString(myItem);
        System.out.println(serialized);
    }

    @Test
    public void custom_serialize () throws JsonProcessingException {

        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addSerializer(Item.class, new ItemSerializer());
        mapper.registerModule(module);

        String serialized = mapper.writeValueAsString(myItem);
        System.out.println(serialized);
    }

}
