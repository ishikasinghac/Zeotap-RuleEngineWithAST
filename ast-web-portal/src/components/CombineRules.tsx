"use client";

import styles from "./Common.module.css";
import axios from "axios";
import { useState } from "react";

export default function CombineRules() {

  const [ruleText, setRuleText] = useState<string>('');
  const [message, setMessage] = useState<string>('');

  const [nodeData, setNodeData] = useState<object | null>(null);

  console.log(nodeData);

  const handleChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setRuleText(e.target.value);
  };

  const combineRule = async (rule: string[]) => {
    try {
      const response = await axios.post('http://localhost:8080/rules/combine', { ruleStrings: rule },
        {
          headers: {
            "Content-Type": "application/json"
          }
        });
      setMessage(`Rule created successfully: ${response.data.id}`);
      setRuleText('');
      return response.data;
    } catch (error) {
      setMessage('Error combineing rule');
    }
  }

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    if (ruleText.trim() === '') {
      setMessage('Please enter a rule');
      return;
    }

    const rulesArray = ruleText.split(",");

    combineRule(rulesArray).then((node: object) => {
      setNodeData(node);
    })

  };

  return (
    <div className={styles.page}>
      {/* <h1 style={{ display: "flex", justifyContent: "center" }}>Combine Rules</h1> */}
      <div className={styles.container}>
        <div>
          <form onSubmit={handleSubmit} className={styles.form}>
            <textarea
              id="textArea"
              name="textArea"
              rows={10}
              cols={33}
              placeholder={'Enter your rule string comma seperated to combine...'}
              onChange={handleChange}
              style={{
                width: '100%',
                padding: '10px',
                fontSize: '16px',
                border: '1px solid #ccc',
                borderRadius: '10px',
                height: "500px",
                background: "#6A9AB0",
                color: "#fff",
                resize: 'vertical', // Allows vertical resizing only
              }}
            />
            <button type="submit" className={styles.button} style={{ marginTop: 30, width: "100%" }}>Combine Rules</button>
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
                nodeData ?
                  JSON.stringify(nodeData, null, 4) : "No Result"
              }
            </code>
          </pre>
        </div>
      </div>
    </div>
  );
}
