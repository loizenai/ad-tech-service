package com.ozenero.adscategorizedservice.model.transfer;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebText {
	String url;
	String content;
}
