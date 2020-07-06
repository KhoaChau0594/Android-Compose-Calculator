package com.practice.calculator.GradesService

import com.practice.calculator.TestExample.GradesService
import com.practice.calculator.TestExample.Student
import io.mockk.*
import org.junit.Test

class test_assertGrades {
    var student: Student = mockkClass(
        Student::class)
    var gradlesService = GradesService()

    @Test
    fun test_goNextGrade(){
        every { student.mark } returns arrayListOf(4.0, 9.0, 6.0)
        gradlesService.assertGrades(student)
    }
}