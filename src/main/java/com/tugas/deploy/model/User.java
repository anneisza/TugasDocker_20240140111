package com.tugas.deploy.model;

import.lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String nama;
    private String nim;
    private String jenisKelamin;
}
