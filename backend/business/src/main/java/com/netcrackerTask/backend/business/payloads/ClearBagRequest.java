package com.netcrackerTask.backend.business.payloads;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

/**
 * This is a request when logs in.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClearBagRequest {

    /**
     * List of accounts ids in bag
     */
    @NotBlank
    List<Long> itemsId;
}
