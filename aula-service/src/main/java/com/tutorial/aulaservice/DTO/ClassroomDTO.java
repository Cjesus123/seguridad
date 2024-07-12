package com.tutorial.aulaservice.DTO;

import com.tutorial.aulaservice.enums.ClassroomType;

public record ClassroomDTO(String name, int capacity, ClassroomType classroomType) {
}
