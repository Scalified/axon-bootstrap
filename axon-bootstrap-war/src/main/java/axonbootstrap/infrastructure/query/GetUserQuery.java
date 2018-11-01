package axonbootstrap.infrastructure.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUserQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

}
