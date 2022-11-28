package com.ozenero.adscategorizedservice.model.transfer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebUrls {
	private List<String> urls;
}
