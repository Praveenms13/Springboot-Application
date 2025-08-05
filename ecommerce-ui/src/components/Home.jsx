import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import apiClient from "../api/apiClient";
import { useLoaderData, useLocation } from "react-router-dom";

export default function Home() {
  // if (error) {
  //   return (
  //     <div
  //       className="flex flex-col items-center justify-center bg-gradient-to-br from-red-50 to-pink-100 dark:from-gray-900 dark:to-red-900 p-6 relative"
  //       style={{ height: "calc(100vh - 140px)" }}
  //     >
  //       {/* Error Icon */}
  //       <div className="w-20 h-20 bg-red-100 dark:bg-red-900/50 rounded-full flex items-center justify-center mb-6 animate-pulse">
  //         <svg
  //           className="w-10 h-10 text-red-500 dark:text-red-400"
  //           fill="none"
  //           stroke="currentColor"
  //           viewBox="0 0 24 24"
  //         >
  //           <path
  //             strokeLinecap="round"
  //             strokeLinejoin="round"
  //             strokeWidth={2}
  //             d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-2.5L13.732 4c-.77-.833-1.964-.833-2.732 0L4.082 16.5c-.77.833.192 2.5 1.732 2.5z"
  //           />
  //         </svg>
  //       </div>
  //       <div className="text-center max-w-md">
  //         <h2 className="text-2xl font-bold text-gray-800 dark:text-gray-100 mb-3">
  //           Oops! Something went wrong
  //         </h2>
  //         <p className="text-gray-600 dark:text-gray-300 mb-6 leading-relaxed">
  //           {error}
  //         </p>
  //         <button
  //           onClick={fetchProducts}
  //           className="bg-gradient-to-r from-red-500 to-pink-500 hover:from-red-600 hover:to-pink-600 dark:from-red-600 dark:to-pink-600 dark:hover:from-red-700 dark:hover:to-pink-700 text-white font-semibold py-3 px-6 rounded-lg shadow-lg transform hover:scale-105 transition-all duration-200 focus:outline-none focus:ring-4 focus:ring-red-200 dark:focus:ring-red-800"
  //         >
  //           Try Again
  //         </button>
  //       </div>
  //       <div className="absolute top-10 left-10 w-4 h-4 bg-red-300 dark:bg-red-600 rounded-full opacity-60 animate-bounce"></div>
  //       <div
  //         className="absolute top-20 right-16 w-3 h-3 bg-pink-400 dark:bg-pink-500 rounded-full opacity-40 animate-bounce"
  //         style={{ animationDelay: "0.5s" }}
  //       ></div>
  //       <div
  //         className="absolute bottom-16 left-20 w-5 h-5 bg-red-200 dark:bg-red-700 rounded-full opacity-50 animate-bounce"
  //         style={{ animationDelay: "1s" }}
  //       ></div>
  //     </div>
  //   );
  // }
  const products = useLoaderData();
  return (
    <div className="max-w-[1152px] mx-auto px-6 py-8">
      <PageHeading title="Explore Our Stickers !">
        Discover a wide range of unique and creative stickers to personalize
        your belongings. From fun designs to inspirational quotes, find the
        perfect sticker to express yourself. Browse our collection and add a
        touch of personality to your life!
      </PageHeading>
      <ProductListings products={products} />
    </div>
  );
}

export async function productLoader() {
  try {
    const response = await apiClient.get("/products");
    return response.data;
  } catch (err) {
    throw new Response(
      err.response?.data?.errorMessage ||
        err.message ||
        "Failed to fetch the products from the data source, try again ...",
      { status: err.status || 500 }
    );
  }
}
