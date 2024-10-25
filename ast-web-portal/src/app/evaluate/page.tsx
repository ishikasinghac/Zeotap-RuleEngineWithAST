"use client";

import { Box, Tab, Tabs } from "@mui/material";
import styles from "./page.module.css";
import axios from "axios";
import { useState } from "react";
import React from "react";
import CreateRule from "@/components/CreateRule";
import CombineRules from "@/components/CombineRules";
import EvaluateRules from "@/components/EvaluateRule";


interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}

function CustomTabPanel(props: TabPanelProps) {
    const { children, value, index, ...other } = props;

    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && <Box sx={{ p: 3 }}>{children}</Box>}
        </div>
    );
}

function a11yProps(index: number) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}


export default function EvaluatePage() {
    const [value, setValue] = useState(0);

    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    return (
        <Box sx={{ width: '100%' }}>
            <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
                <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
                    <Tab label="Create Rules" {...a11yProps(0)} />
                    <Tab label="Combine Rules" {...a11yProps(1)} />
                    <Tab label="Evaluate Rules" {...a11yProps(2)} />
                </Tabs>
            </Box>
            <CustomTabPanel value={value} index={0}>
                <CreateRule />
            </CustomTabPanel>
            <CustomTabPanel value={value} index={1}>
                <CombineRules />
            </CustomTabPanel>
            <CustomTabPanel value={value} index={2}>
               <EvaluateRules />
            </CustomTabPanel>
        </Box>
    );
}
