/*
 * Copyright (c) 2008-2015, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.collection.impl.list.operations;

import com.hazelcast.collection.impl.collection.CollectionContainer;
import com.hazelcast.collection.impl.collection.CollectionDataSerializerHook;
import com.hazelcast.collection.impl.list.ListContainer;
import com.hazelcast.collection.impl.collection.operations.CollectionReplicationOperation;
import com.hazelcast.nio.ObjectDataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ListReplicationOperation extends CollectionReplicationOperation {

    public ListReplicationOperation() {
    }

    public ListReplicationOperation(Map<String, CollectionContainer> migrationData, int partitionId, int replicaIndex) {
        super(migrationData, partitionId, replicaIndex);
    }

    @Override
    protected void readInternal(ObjectDataInput in) throws IOException {
        int mapSize = in.readInt();
        migrationData = new HashMap<String, CollectionContainer>(mapSize);
        for (int i = 0; i < mapSize; i++) {
            String name = in.readUTF();
            ListContainer container = new ListContainer();
            container.readData(in);
            migrationData.put(name, container);
        }
    }

    @Override
    public int getId() {
        return CollectionDataSerializerHook.LIST_REPLICATION;
    }
}
