# 시간초과
import sys

def get_nearest_node(set, distances):
    nearest_node = 0
    for node in edges.keys():
        if node not in set and distances[node] < distances[nearest_node]:
            nearest_node = node
    return nearest_node

def get_distance(frm, targets):
    distances = [sys.maxsize for _ in range(P+1)]
    distances[frm] = 0
    m_set = set()
    while len(m_set) < P:
        nearest_node = get_nearest_node(m_set, distances)
        m_set.add(nearest_node)
        is_early_return = True
        for target in targets:
            if target not in m_set:
                is_early_return = False
                break
        if is_early_return: 
            break
        for node in edges[nearest_node].keys():
            if node not in m_set and\
                distances[nearest_node]+edges[nearest_node][node] < distances[node]:
                distances[node] = distances[nearest_node] + edges[nearest_node][node]
    
    return distances

C, P, PB, PA1, PA2 = map(int, input().split())
edges = {}
for i in range(1, P+1):
    edges[i] = {}
    
for _ in range(C):
    frm, to, dist = map(int, input().split())
    edges[frm][to] = dist
    edges[to][frm] = dist
    
distsPB = get_distance(PB, {PA1, PA2})
distsPA = get_distance(PA1, {PA2})[PA2]

print(min(distsPB[PA1], distsPB[PA2]) + distsPA)
