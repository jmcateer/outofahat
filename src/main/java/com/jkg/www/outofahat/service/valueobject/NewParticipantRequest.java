package com.jkg.www.outofahat.service.valueobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NewParticipantRequest {
    private String first;
    private String last;
    private String email;
    private String phone;
    private List<Integer> ineligibles;
    private List<Integer> previous;
}
