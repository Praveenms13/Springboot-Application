import React from "react";

export default function SearchBox({ label, placeholder, value, handleSearch }) {
  return (
    <div className="flex items-center gap-3 pl-4 flex-1 font-primary">
      <label className="text-lg font-semibold text-primary dark:text-light">
        {label}
      </label>
      <input
        type="text"
        placeholder={placeholder}
        value={value}
        onChange={(event) => handleSearch(event.target.value)}
        className="px-4 py-2 text-base border rounded-md transition border-primary dark:border-light 
                  focus:outline-none focus:ring-2 focus:ring-white dark:focus:ring-white 
                 text-gray-800 dark:text-gray-100 placeholder-gray-400 dark:placeholder-gray-500"
      />
    </div>
  );
}
