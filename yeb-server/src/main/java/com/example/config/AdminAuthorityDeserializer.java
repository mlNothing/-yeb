package com.example.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @ProjectName:yeb
 * @Package:com.zlq.server.config
 * @ClassName: AdminAuthorityDeserializer
 * @description: 自定义Authority的Json反序列化解析器
 *  * @author mlNothing
 *  * @date 2021/12/13 17:35
 */
public class AdminAuthorityDeserializer extends JsonDeserializer {
    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
        JsonNode jsonNode = mapper.readTree(jsonParser);
        List<GrantedAuthority> authorityList = new LinkedList<>();
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()){
            JsonNode next = elements.next();
            JsonNode authority = next.get("authority");
            authorityList.add(new SimpleGrantedAuthority(authority.asText()));
        }
        return authorityList;
    }
}


