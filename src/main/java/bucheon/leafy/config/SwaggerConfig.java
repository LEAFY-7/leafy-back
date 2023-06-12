package bucheon.leafy.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("leafy")
                .version("v1")
                .description("Leafy Open Api");
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .scheme("apiKey")
                .name("Authorization")
                .in(SecurityScheme.In.HEADER);
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Auth");
        return new OpenAPI().components(new Components().addSecuritySchemes("Auth", securityScheme))
                .info(info)
                .security(Arrays.asList(securityRequirement));
    }
}
