import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import products from "../data/products"; 
export default function Home() {
  return (
    <div className="home-container">
      <PageHeading title="Explore Our Stickers !">
        Discover a wide range of unique and creative stickers to personalize
        your belongings. From fun designs to inspirational quotes, find the
        perfect sticker to express yourself. Browse our collection and add a
        touch of personality to your life!
      </PageHeading>
      <ProductListings products={products}/>
    </div>
  );
}
