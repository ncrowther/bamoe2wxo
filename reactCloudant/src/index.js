import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Fine from './Fine.js';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';

const queryClient = new QueryClient();

ReactDOM.createRoot(document.getElementById('root')).render(
    <React.StrictMode>
      <QueryClientProvider client={queryClient}>
        <Fine />
      </QueryClientProvider>

    </React.StrictMode>
  )

