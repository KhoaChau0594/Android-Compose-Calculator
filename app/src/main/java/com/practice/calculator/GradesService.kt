package com.practice.calculator


data class Student (val name: String, val mark: ArrayList<Double>){

}


class GradesService {
    fun averageGrades(student: Student): Double{
        return student.mark.average()
    }

    fun goNextGrade() = println("Next Grade")
    fun cannotGoNextGrade() = println("No")

    fun assertGrades(student: Student){
        val avg = averageGrades(student)
        if(avg > 5.0){
            goNextGrade()
        } else {
            cannotGoNextGrade()
        }
    }
}


