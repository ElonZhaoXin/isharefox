package com.isharefox.share.web.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Page {
    private String title;
    private String description;
    private String templateName;
}
