package com.ct271.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
		@NotBlank(message="Email không thể bỏ trống")
		@Email(message="Email không hợp lệ")
		@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không hợp lệ")
		String email,
		@Size(min=6, max=30, message="Mật khẩu phải từ 6 đến 30 ký tự")
		@NotBlank(message="Mật khẩu không thể bỏ trống")
		String password,
		@NotBlank(message="Sdt không thể bỏ trống")
//		@Pattern(regexp = "^[0-9\\-\\+]{9,15}$\r\n", message = "Số điện thoại không hợp lệ")
		String phone,
		@NotBlank(message="Address không thể bỏ trống")
		String address,
		@NotBlank(message="Tên không thể bỏ trống")
		@Size(max=18, message="Tên không được quá 15 kí tự")
		String username
		) {

}
