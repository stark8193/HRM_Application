import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LeaveType from './leave-type';
import LeaveTypeDetail from './leave-type-detail';
import LeaveTypeUpdate from './leave-type-update';
import LeaveTypeDeleteDialog from './leave-type-delete-dialog';

const LeaveTypeRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LeaveType />} />
    <Route path="new" element={<LeaveTypeUpdate />} />
    <Route path=":id">
      <Route index element={<LeaveTypeDetail />} />
      <Route path="edit" element={<LeaveTypeUpdate />} />
      <Route path="delete" element={<LeaveTypeDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LeaveTypeRoutes;
