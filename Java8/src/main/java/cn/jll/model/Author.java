package cn.jll.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode      // 用于后期去重
public class Author {
    private Long id;

    private String name;

    private Integer age;

    private String intro;

    private List<Book> books;
}
