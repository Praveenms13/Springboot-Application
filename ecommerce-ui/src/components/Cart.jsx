import React, { useMemo } from "react";
import PageTitle from "./PageTitle";
import { Link } from "react-router-dom";
import emptyCartImage from "../assets/util/emptycart.png";
import { useCart } from "../store/cart-context";
import CartTable from "./CartTable";
import { useAuth } from "../store/auth-context";
import { toast } from "react-toastify";

export default function Cart() {
  const { cart } = useCart();
  4;
  const { isAuthenticated, user } = useAuth();

  const isAddressIncomplete = useMemo(() => {
    if (!isAuthenticated) return false;
    if (!user || !user.address) return true;
    const { state, city, street, postalCode, country } = user.address;
    return !state || !city || !street || !postalCode || !country;
  }, [user]);

  const isCartEmpty = useMemo(() => cart.length === 0, [cart.length]);

  return (
    <div className="min-h-[852px] py-12 bg-normalbg dark:bg-darkbg font-primary">
      <div className="max-w-4xl mx-auto px-4">
        <PageTitle title="Your Cart" />
        {!isCartEmpty ? (
          <>
            {isAddressIncomplete && (
              <div className="bg-yellow-100 text-yellow-800 p-4 mb-6 rounded-md">
                <p className="text-lg">
                  Please complete your address details in your profile to
                  proceed with checkout.
                </p>
                <Link
                  to="/profile"
                  className="text-blue-600 hover:underline mt-2 inline-block"
                >
                  Go to Profile
                </Link>
              </div>
            )}
            <CartTable />
            <div className="flex justify-between mt-8 space-x-4">
              <Link
                to="/home"
                className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition"
              >
                Back to Products
              </Link>
              <Link
                to="/checkout"
                className={`py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition ${
                  isAddressIncomplete ? "opacity-50 cursor-not-allowed" : ""
                }`}
                aria-disabled={isAddressIncomplete}
                onClick={(e) => {
                  if (isAddressIncomplete) {
                    e.preventDefault();
                    toast.error(
                      "Please complete your address details in your profile to proceed with checkout."
                    );
                  }
                }}
              >
                Proceed to Checkout
              </Link>
            </div>
          </>
        ) : (
          <div className="text-center text-gray-600 dark:text-lighter flex flex-col items-center">
            <p className="max-w-[576px] px-2 mx-auto text-base mb-4">
              Oops... Your cart is empty. Continue shopping
            </p>
            <img
              src={emptyCartImage}
              alt="Empty Cart"
              className="max-w-[300px] mx-auto mb-6 dark:bg-light dark:rounded-md"
            />
            <Link
              to="/home"
              className="py-2 px-4 bg-primary dark:bg-light text-white dark:text-black text-xl font-semibold rounded-sm flex justify-center items-center hover:bg-dark dark:hover:bg-lighter transition"
            >
              Back to Products
            </Link>
          </div>
        )}
      </div>
    </div>
  );
}
