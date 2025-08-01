import React, { useState, useMemo } from "react";
import ProductCard from "./ProductCard";
import SearchBox from "./SearchBox";
import Dropdown from "./Dropdown";

const sortList = ["Popularity", "Price: Low to High", "Price: High to Low"];

export default function ProductListings({ products }) {
  const [searchText, setSearchText] = useState("");
  const [selectedSort, setSelectedSort] = useState("Popularity");

  const filteredAndSortedProducts = useMemo(() => {
    if (!Array.isArray(products) || products.length === 0) {
      return [];
    }
    let filtered = products.filter(
      (product) =>
        product.name.toLowerCase().includes(searchText.toLowerCase()) ||
        product.description.toLowerCase().includes(searchText.toLowerCase())
    );

    return filtered.slice().sort((a, b) => {
      switch (selectedSort) {
        case "Price: Low to High":
          return parseFloat(a.price) - parseFloat(b.price);
        case "Price: High to Low":
          return parseFloat(b.price) - parseFloat(a.price);
        case "Popularity":
        default:
          return parseInt(b.popularity) - parseInt(a.popularity);
      }
    });
  }, [products, searchText, selectedSort]);

  const handleSearch = (inputSearch) => setSearchText(inputSearch);
  const handleSort = (sortType) => setSelectedSort(sortType);

  return (
    <div className="max-w-[1152px] mx-auto">
      <div className="flex flex-col sm:flex-row justify-between items-center gap-4 pt-12">
        <SearchBox
          label="Search"
          placeholder="Search by name or category"
          value={searchText}
          handleSearch={handleSearch}
        />
        <Dropdown
          label="Sort By"
          options={sortList}
          selectedValue={selectedSort}
          handleSort={handleSort}
        />
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
