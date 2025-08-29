import React, { useEffect } from 'react';
import apiClient from "../api/apiClient";

export default function Orders() {
  useEffect(() => {
    const updateProfile = async () => {
      try {
        const profileData = { /* your data here */ };
        const response = await apiClient.get("/orders", profileData);
        console.log(response.data);
      } catch (error) {
        console.error("Error updating profile:", error);
      }
    };

    updateProfile();
  }, []);

  return (
    <div>
      Orders
    </div>
  );
}
