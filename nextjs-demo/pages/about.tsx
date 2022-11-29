import Head from 'next/head'
import Image from 'next/image'
import styles from '../styles/Home.module.css'
import {NextPage} from "next";


const About : NextPage = () => {
    return (
        <div className={styles.container}>
            <h1>About Santhosh</h1>
        </div>
    );
}

export default About;
