import React from "react";

export default function Dropdown({
  label,
  options,
  selectedValue,
  handleSort,
}) {
  return (
    <div className="flex items-center gap-2 justify-end pr-12 flex-1 font-primary">
      <label className="text-lg font-semibold text-primary dark:text-light">
        {label}
      </label>
      <select
        value={selectedValue}
        onChange={(event) => handleSort(event.target.value)}
        className="px-4 py-2 text-base border rounded-md transition border-primary dark:border-light focus:ring focus:ring-dark dark:focus:ring-lighter focus:outline-none text-gray-800 dark:text-lighter bg-white dark:bg-gray-800"
      >
        {options.map((optionVal, index) => (
          <option
            key={index}
            value={optionVal}
            className="bg-white dark:bg-gray-800 text-gray-800 dark:text-lighter"
          >
            {optionVal}
          </option>
        ))}
      </select>
    </div>
  );
}
