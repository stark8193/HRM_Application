import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LeaveRequest from './leave-request';
import LeaveRequestDetail from './leave-request-detail';
import LeaveRequestUpdate from './leave-request-update';
import LeaveRequestDeleteDialog from './leave-request-delete-dialog';

const LeaveRequestRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LeaveRequest />} />
    <Route path="new" element={<LeaveRequestUpdate />} />
    <Route path=":id">
      <Route index element={<LeaveRequestDetail />} />
      <Route path="edit" element={<LeaveRequestUpdate />} />
      <Route path="delete" element={<LeaveRequestDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LeaveRequestRoutes;
