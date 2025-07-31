import React, { useState } from "react";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";
import Dropdown from "./Dropdown";

const sortList = ["Price: Low to High", "Price: High to Low", "Newest"];
export default function ProductListings({ products }) {
  const [searchText, setSearchText] = useState("");

  function handleSearch(inputSearch) {
    setSearchText(inputSearch);
  }

  let filteredAndSortedProducts = Array.isArray(products)
    ? products.filter((product) => {
        return (
          product.name.toLowerCase().includes(searchText.toLowerCase()) ||
          product.description.toLowerCase().includes(searchText.toLowerCase())
        );
      })
    : [];

  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4 pt-12">
        <SearchBox
          label="Search"
          placeholder="Search by name or category"
          value={searchText}
          handleSearch={(value) => handleSearch(value)}
        />
        <Dropdown label="Sort By" options={sortList} selectedValue="" />
      </div>
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-y-8 gap-x-6 py-12">
        {filteredAndSortedProducts.length > 0 ? (
          filteredAndSortedProducts.map((product) => (
            <ProductCard key={product.productId} product={product} />
          ))
        ) : (
          <p className="text-center font-primary font-bold text-lg text-primary">
            No products found
          </p>
        )}
      </div>
    </div>
  );
}
