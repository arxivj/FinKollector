package com.miniproject.finkollector.exception

class InvalidEmailException(message: String) : RuntimeException(message)
class DuplicateEmailException(message: String) : RuntimeException(message)