import Head from "next/head";
import Image from "next/image";
import styles from "../styles/Home.module.css";
import Footer from "../components/Layout/Footer";
import ReactDOM from "react-dom/client";
import Search from "../components/Search";
import Drawer from "../components/Drawer";
import FarmerLeaderboard from "../components/Leaderboards/FarmerLeaderboard";
import ProfilePage from "../components/Profiles/ProfilePage";
import PrimarySearchAppBar from "../components/Layout/PrimarySearchAppBar";
import PageVisitsTable from "../components/Tables/PageVisitsTable";
import Hero from "../components/HomeSections/Hero";
import WhyGlean from "../components/HomeSections/WhyGlean";
import MeetTopVolunteers from "../components/HomeSections/MeetTopVolunteers";
import { Navbar } from "@themesberg/react-bootstrap";
import SignInSide from "../components/Login";
import SignUp from "../components/SignUp"
import Navibar from "../components/NaviBar";

export default function Home(props) {
  return (
    <div>
      <Head>
        <title>Title</title>
        <meta name="description" content="Generated by create next app" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <main>
        {/*<Navbar />*/}
        {/*<Search />*/}
        {/*<Hero />*/}
        {/*<WhyGlean />*/}
        {/*<MeetTopVolunteers />*/}
        {/*<PrimarySearchAppBar/>*/}
        {/*<ProfilePage /> */}
        {/*<Footer />*/}
          <SignInSide />
      </main>
    </div>
  );
}
