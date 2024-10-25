"use client";

import styles from "./Common.module.css";
import axios from "axios";
import { useState } from "react";

export default function EvaluateRules() {

    const [ruleText, setRuleText] = useState<string>('');
    const [message, setMessage] = useState<string>('');
    const [data, setData] = useState<string>('');

    const [result, setResult] = useState<object | null>(null);

    // console.log(result);

    const handleChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        setRuleText(e.target.value);
    };

    const handleChangeData = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
        setData(e.target.value);
    };

    const evaluateRule = async (ast: object, data: object) => {
        try {
            const response = await axios.post('http://localhost:8080/rules/evaluate', { ast: ast, data: data },
                {
                    headers: {
                        "Content-Type": "application/json"
                    }
                });
            setMessage(`Rule evaluated successfully: ${response.data.id}`);
            // setRuleText('');
            return response.data;
        } catch (error) {
            setMessage('Error evaluating rule');
        }
    }

    const handleSubmit = async (event: React.FormEvent) => {
        setResult(null);
        event.preventDefault();
        if (ruleText.trim() === '' || data.trim() === '') {
            setMessage('Please enter a rule');
            console.log("Empty")
            return;
        }

        console.log("herere")
        const ast = JSON.parse(ruleText);
        const d = JSON.parse(data)

        evaluateRule(ast, d)
            .then((result) => {
                console.log("Res-", result)
                setResult(result);
            })

    };

    return (
        <div className={styles.page}>
            <div className={styles.container}>

                <div>
                    <form onSubmit={handleSubmit} className={styles.form}>
                        <textarea
                            id="textArea"
                            name="textArea"
                            rows={10}
                            cols={33}
                            placeholder={'Enter your ast here...'}
                            // value={value}
                            onChange={handleChange}
                            style={{
                                width: '100%',
                                padding: '10px',
                                fontSize: '16px',
                                border: '1px solid #ccc',
                                borderRadius: '10px',
                                height: "200px",
                                background: "#6A9AB0",
                                color: "#fff",
                                resize: 'vertical', // Allows vertical resizing only
                            }}
                        />
                        <textarea
                            id="textArea"
                            name="textArea"
                            rows={10}
                            cols={33}
                            placeholder={'Enter your data object...'}
                            // value={value}
                            onChange={handleChangeData}
                            style={{
                                marginTop: "15px",
                                width: '100%',
                                padding: '10px',
                                fontSize: '16px',
                                border: '1px solid #ccc',
                                borderRadius: '10px',
                                height: "200px",
                                background: "#6A9AB0",
                                color: "#fff",
                                resize: 'vertical', // Allows vertical resizing only
                            }}
                        />
                        <button type="submit" className={styles.button} style={{ marginTop: 30, width: "100%" }}>Evaluate Rule</button>
                    </form>
                </div>
                <div>
                    <pre style={{
                        backgroundColor: '#001F3F',
                        color: "#EAD8B1",
                        padding: '15px',
                        borderRadius: '8px',
                        overflowX: 'auto',
                        fontFamily: 'monospace',
                        fontSize: '14px',
                        minWidth: "500px",
                        height: '550px',
                        overflowY: "auto",
                    }}>
                        <code>
                            {
                                result !== null ?
                                    JSON.stringify(result) : "No Result"
                            }
                        </code>
                    </pre>
                </div>
            </div>
        </div>
    );
}
