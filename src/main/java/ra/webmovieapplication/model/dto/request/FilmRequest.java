package ra.webmovieapplication.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FilmRequest {
    private Integer id;
    @NotBlank(message = "Must not be blank")
    private String name;
    @NotBlank(message = "Must not be blank")
    private String description;
    private MultipartFile file;
    private Boolean isFree;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Must not be blank")
    private Date releasedDate;
    @NotNull(message = "Must not be blank")
    private Long time;
    private Boolean status;
    @NotNull(message = "Must not be blank")
    private Boolean seriesSingle;
    private Integer totalEpisode;
    private Integer countryId;
    @NotEmpty(message = "At least one actor must be selected")
    private Set<Long> actorsId;
    @NotEmpty(message = "At least one director must be selected")
    private Set<Long> directorsId;
    @NotEmpty(message = "At least one category must be selected")
    private Set<Integer> categoryId;

}
