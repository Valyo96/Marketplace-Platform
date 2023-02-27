package com.platform.marketplace.Marketplace.Platform.dto;

import com.platform.marketplace.Marketplace.Platform.utility.annotations.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrgPasswordChange {
    private String oldPassword;
    @Password
    private String newPassword;
    private String confirmNewPassword;
}
