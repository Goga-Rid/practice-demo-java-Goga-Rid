package com.example.demoexam;

import lombok.*;

import java.sql.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FMJob {
    private int memberJob_id;
    private int memberId;
    private String fio;
    private String position;
    private String org_name;
    private int salary;
    private Date dateOfBirth;
}
