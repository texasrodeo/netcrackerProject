package com.netcrackerTask.backend.business.payloads;

import javax.validation.constraints.NotBlank;

public class PayRequest {
    @NotBlank
    private String sum;

    public Long getSum() {
        return Long.parseLong(sum);
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
