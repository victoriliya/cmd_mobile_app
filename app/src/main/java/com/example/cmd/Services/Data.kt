package com.example.cmd.Services

import com.example.cmd.Models.Departments
import com.example.cmd.Models.Leadership

object Data {

    val leaders = listOf<Leadership>(
        Leadership("MR. B.D CHINOKO", "AG. DIRECTOR GENERAL/CEO", "dg"),
        Leadership("MR. k.M ABDULKAREEM", "DIRECTOR ACCREDITATION DEPT", "kabir"),
        Leadership("MR. J.B MYHA", "DIRECTOR LEARNING AND DEVELOPMENT DEPT", "myha"),
        Leadership("DR. MRS. M.O OLUSOJI", "DIRECTOR ECONOMIC MANAGEMENT DEPT", "olusoji"),
        Leadership("MR. M NUHU", "AG DIRECTOR ACADAMIC HUMAN RESOURCES", "nuhu"),
        Leadership("MS. D.O. ESIRI", "", "esiri"),
        Leadership("MR. O.O CHUKWUMAEZE", "", "chukumaeze"),
        Leadership("MRS. O.A ADEYEMO", "", "adeyemo")
    )

    val department = listOf<Departments>(
        Departments("Office of the director general"),
        Departments("ICT"),
        Departments("Accreditation"),
        Departments("Library"),
        Departments("Learning and skill and development"),
        Departments("Admin"),
        Departments("Office of the director general"),
        Departments("ICT"),
        Departments("Accreditation"),
        Departments("Library"),
        Departments("Learning and skill and development"),
        Departments("Admin")
    )

}