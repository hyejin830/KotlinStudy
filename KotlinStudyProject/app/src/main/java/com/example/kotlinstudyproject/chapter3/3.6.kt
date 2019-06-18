package com.example.kotlinstudyproject.chapter3

/*
코드 다듬기 : 로컬 함수와 확장

흔히 발생하는 코드 중복을 로컬 함수를 통해 제거할 수 있다

*/

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException(
            "can't"
        )
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException(
            "can't"
        )
    }
}

fun saveUser2(user: User) {
    fun validate(user: User, value: String, fileName: String) {
        if (value.isEmpty()) {
            throw java.lang.IllegalArgumentException(
                "can't"
            )
        }
    }

    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
}

fun main() {
    saveUser(User(1, "", ""))
}
