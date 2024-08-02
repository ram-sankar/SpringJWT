package com.example.demo.dataTransferObject;
import com.example.demo.models.TestBlueprint;
import lombok.Data;

import java.util.List;

@Data
public class TestBlueprintResponse {
    private List<TestBlueprint> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
}
