import React from "react";

export default function Price({ currency, price }) {
  return (
    <>
      {currency}
      <span>{price.toFixed(2)}</span>
    </>
  );
}
