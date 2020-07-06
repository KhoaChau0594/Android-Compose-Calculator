package com.practice.calculator.GradesService

import com.practice.calculator.TestExample.GradesService
import com.practice.calculator.TestExample.Student
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import org.junit.Test

class test_averageGrades {
    var student: Student = mockk<Student>()
    var gradlesService = GradesService()

    @Test
    fun test_average(){
        /* tạo stub cho đối tượng student */
        every { student.mark } returns arrayListOf(4.0, 3.0, 5.0)
        val avg = gradlesService.averageGrades(student)
        /* kiểm tra dữ liệu */
        assertEquals("Wrong",5.0, avg)
    }

    @Test
    fun test_average2(){
        /* tạo stub cho đối tượng student */
        every { student.mark } returns arrayListOf(4.0, 9.0, 6.0)
        val avg = gradlesService.averageGrades(student)
        /* kiểm tra dữ liệu */
        assertEquals("Wrong",5.0, avg)
    }

}