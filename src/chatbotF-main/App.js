import React from 'react';
import Chatbot from './Chatbot';

const ChatbotApp = ({ onBackToDashboard, onLogout }) => {
  return (
    <Chatbot 
      onBackToDashboard={onBackToDashboard}
      onLogout={onLogout}
    />
  );
};

export default ChatbotApp;