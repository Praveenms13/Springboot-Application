import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import products from "../data/products";
import BootstrapButton from "./BootstrapButton";

export default function Home() {
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
