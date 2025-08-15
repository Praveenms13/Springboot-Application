import React, { useEffect } from "react";
import PageTitle from "./PageTitle";
import {
  Link,
  Form,
  useActionData,
  useNavigation,
  useNavigate,
} from "react-router-dom";
import apiClient from "../api/apiClient";
import { toast } from "react-toastify";

export default function Login() {
  const actionData = useActionData();
  const navigation = useNavigation();
  const isSubmitting = navigation.state === "submitting";
  const navigate = useNavigate();
  useEffect(() => {
    if (actionData?.succcess) {
      navigate("/home");
    } else if (actionData?.errors) {
      toast.error(actionData.errors.message || "Login Failed");
    }
  }, [actionData]);
  const labelStyle =
    "block text-lg font-semibold text-primary dark:text-light mb-2";
  const textFieldStyle =
    "w-full px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-600 placeholder-gray-400 dark:placeholder-gray-300";

  return (
    <div className="h-[calc(100vh-160px)] w-full flex items-center justify-center font-primary dark:bg-darkbg overflow-hidden">
      <div className="bg-white dark:bg-gray-700 shadow-md rounded-lg max-w-sm w-full mx-4 px-6 py-5">
        <PageTitle title="Login" />
        <Form method="POST" className="space-y-4">
          <div>
            <label htmlFor="username" className={labelStyle}>
              Username
            </label>
            <input
              id="username"
              type="text"
              name="username"
              placeholder="Your Username"
              autoComplete="username"
              required
              className={textFieldStyle}
            />
          </div>
          <div>
            <label htmlFor="password" className={labelStyle}>
              Password
            </label>
            <input
              id="password"
              type="password"
              name="password"
              placeholder="Your Password"
              autoComplete="current-password"
              required
              minLength={4}
              maxLength={20}
              className={textFieldStyle}
            />
          </div>

          <div>
            <button
              type="submit"
              disabled={isSubmitting}
              className="w-full px-6 py-2 text-white dark:text-black text-xl rounded-md transition duration-200 bg-primary dark:bg-light hover:bg-dark dark:hover:bg-lighter"
            >
              {isSubmitting ? "Authenticating ...." : "Login"}
            </button>
          </div>
        </Form>

        <p className="text-center text-gray-600 dark:text-gray-400 mt-4">
          Don't have an account?{" "}
          <Link
            to="/register"
            className="text-primary dark:text-light hover:text-dark dark:hover:text-primary transition duration-200"
          >
            Register Here
          </Link>
        </p>
      </div>
    </div>
  );
}

export async function loginAction({ request }) {
  const data = await request.formData();
  const loginData = {
    username: data.get("username"),
    password: data.get("password"),
  };
  try {
    const response = await apiClient.post("/auth/login", loginData);
    const { message, user, JwtToken } = response.data;
    return { succcess: true, message, user, JwtToken };
  } catch (error) {
    if (error.response?.status === 401) {
      return {
        success: false,
        errors: {
          message: "Invalid username or password",
        },
      };
    }
    throw new Response(
      error.response?.data?.message ||
        error.message ||
        "Failed to login, Please try again ...",
      { status: error.response?.status || 500 }
    );
  }
}
