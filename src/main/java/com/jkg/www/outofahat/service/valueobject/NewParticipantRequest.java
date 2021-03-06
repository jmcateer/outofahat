package com.jkg.www.outofahat.service.valueobject;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewParticipantRequest {

    private String first;
    private String last;
    private String email;
    private String phone;
    private List<String> ineligibles;
    private List<String> previous;
}
