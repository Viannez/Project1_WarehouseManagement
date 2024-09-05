import React from 'react';
import {render, screen } from '@testing-library/react';
import { act } from 'react';
import WarehousePage from '../WarehousePage';

describe('Warehouse Page', () => {
    afterEach(() => {
        jest.clearAllMocks();
    });

    test('should render the warehouses title', () => {
         act( () => {
            render(<WarehousePage />);
        });
    
        expect(screen.getByText('Warehouses')).toBeInTheDocument();
    });

    test('should render the sort warehouses label', () => {
        act( () => {
           render(<WarehousePage />);
       });
   
       expect(screen.getByText('Sort warehouses by:')).toBeInTheDocument();
   });
})