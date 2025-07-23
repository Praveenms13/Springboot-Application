import React from "react";

export default function BootstrapButton({ text, onClick, className }) {
  return (
    <div className={`btn ${className}`} onClick={onClick}>
      {text}
    </div>
  );
}
