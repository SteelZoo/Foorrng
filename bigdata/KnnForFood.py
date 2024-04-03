import pandas as pd
from sklearn.neighbors import NearestNeighbors
import numpy as np
import matplotlib.pyplot as plt

#  KNN 이랑 시계열, 시설 데이터 합치고
# 데이터를 리스트로 뽑는다.
features = np.array([
    [2, 3, 5, 7, 1],  # 덮밥 (Rice Bowl)
    [5, 4, 6, 9, 2],  # 전기구이통닭 (Electric Grilled Whole Chicken)
    [4, 2, 5, 8, 1],  # 꼬치 (Skewer)
    [2, 3, 4, 6, 7],  # 타코야끼 (Takoyaki)
    [4, 3, 5, 8, 2],  # 타코 & 케밥 (Taco & Kebab)
    [5, 3, 6, 3, 7],  # 분식 (Korean Street Food)
    [1, 6, 3, 1, 9],  # 빵 (Bread)
    [1, 4, 2, 1, 0],  # 구황작물 (Famine Crops)
    [0, 8, 1, 0, 6],  # 카페 & 디저트 (Cafe & Dessert)
    [5, 5, 5, 5, 5],  # 기타 (Etc)
])
H = np.array(
    [[2, 2, 3, 5, 8], # Rice Bowl
    [2, 3, 5, 9, 0], # Electric Grilled Whole Chicken
    [3, 2, 4, 8, 0], # Skewer
    [1, 3, 4, 4, 7], # Takoyaki
    [3, 2, 5, 7, 6], # Taco & Kebab
    [6, 2, 6, 2, 10], # Formula (unknown)
    [0, 2, 3, 0, 10], # Bread
    [0, 1, 2, 0, 9], # Old Crop
    [1, 8, 2, 0, 5], # Cafe & Dessert
    [5, 5, 5, 5, 5]
])
C = np.array([
    [2, 3, 5, 7, 1],  # 덮밥
    [5, 4, 6, 9, 2],  # 전기구이통닭
    [4, 2, 5, 8, 1],  # 꼬치
    [2, 3, 4, 6, 7],  # 타코야끼
    [4, 3, 5, 8, 2],  # 타코 & 케밥
    [5, 3, 6, 3, 7],  # 분식
    [1, 6, 3, 1, 9],  # 빵
    [1, 4, 2, 1, 0],  # 구황작물
    [0, 8, 1, 0, 6],  # 카페 & 디져트
    [5, 5, 5, 5, 5]  #기타
])
D = np.array([
    [2, 3, 5, 7, 1],  # 덮밥
    [5, 4, 6, 9, 2],  # 전기구이통닭
    [4, 2, 5, 8, 1],  # 꼬치
    [2, 3, 4, 6, 7],  # 타코야끼
    [4, 3, 5, 8, 2],  # 타코 & 케밥
    [5, 3, 6, 3, 7],  # 분식
    [1, 6, 3, 1, 9],  # 빵
    [1, 4, 2, 1, 0],  # 구황작물
    [0, 8, 1, 0, 8],  # 카페 & 디져트
    [5, 5, 5, 5, 5]  #기타
])
E = np.array([
#[spicy, sweet, salt, meat, flour]
    [2, 3, 5, 7, 1],  # 덮밥
    [5, 4, 6, 9, 2],  # 전기구이통닭
    [4, 2, 5, 8, 1],  # 꼬치
    [2, 3, 4, 6, 7],  # 타코야끼
    [4, 3, 5, 8, 2],  # 타코 & 케밥
    [6, 1, 8, 4, 9],  # 분식
    [0, 8, 3, 2, 10],  # 빵
    [2, 5, 2, 3, 0],  # 구황작물
    [0, 10, 3, 0, 8],  # 카페 & 디져트
    [5, 5, 5, 5, 5]  #기타
])

# print(C+H+X+features)

# 데이터 쎗 결합
# combined_features = C + H + X + features 도 되는데 이건 배열을 걍 더하는 거라 수직 방향으로 배열을 이어주는 vstack 메소드를 이용 했다 .
combined_features = np.vstack([C,H,features,D,E])

# 모델 학습 지도 기반인 knn은 게으른 학습기이다. 판별 함수가 아닌 훈련 데이터 셋을 메모리에 저장하는 구조이기 떄문. 따라서 학습이 아니다..!
# 걍 통계를 위한 저장 용도일 뿐.
# n_negithbors 는 홀수로 사용하는 것을 추천한댄다
knn = NearestNeighbors(n_neighbors=5, metric='euclidean')
knn.fit(combined_features)


#<--여기까진 KNN을 통한 유사도 통계를 낸 거기 떄문에 매번 할 필요 없이 따로 저장을 해둔다면 좋을 듰?


# Step 0: KNN을 활용한 값 추천
## 2차원 표로 보여주기 위한 시각화를 위한 scatter plot 임
## 재밌는건 속성이 여러개이기 때문에 5개 중 2개만 택해서 각각의 축에 매핑시키고 볼 수 있음
## knn의 kneighbors를 쓰게 되면, 알아서 비슷한 그룹으로 찾아내줌
labels = ['Rice Bowl', 'Electric Grilled Whole Chicken', 'Skewer', 'Takoyaki', 'Taco & Kebab',
          'Korean Street Food', 'Bread', 'Famine Crops', 'Cafe & Dessert', 'Etc'] * 5
# # Colors for each food category (repeated for C, H, X datasets)
colors = ['blue', 'green', 'red', 'cyan', 'magenta', 'yellow', 'black', 'orange', 'purple', 'brown'] * 5
# Plotting salt (3rd feature) vs meat (4th feature)
#[spicy, sweet, salt, meat, flour]
# plt.figure(figsize=(10, 8))
# for i in range(len(combined_features)):
#     plt.scatter(combined_features[i, 0], combined_features[i, 1], label=labels[i] if i < 10 else "", color=colors[i], alpha=0.6)
# plt.xlabel('1')
# plt.ylabel('2')
# plt.title('Food Characteristics')
# plt.legend()
# plt.show()
### 2차원 표로 보여주기 위한 라이브러링~ 로직~~ 끘

# Input Item
# 해당 동네에 살고 있는 사용자들의 선호 음식 입력 데이터 값을 입력하고 앙상블에 올리기 위한 데이터를 추가한다.
# 곡물 치킨, 치킨 에 대해 저장을 해보자.
#[spicy, sweet, salt, meat, flour]
user_preference  = np.array([[3, 2, 3, 2, 8]])
user_preference1 = np.array([[4, 0, 6, 10, 4]])
user_preference2 = np.array([[4, 2, 7, 8, 7]])

# Find the 이웃
# 거리는 낮은 데이터로 나올 수록 해당 이웃과의 근접함을 나타낸다.
# indices 색인 : 어떤 데이터가 가까운지 알려준다.
# 사용자 3 명의 데이터를 저장한다.
distances, indices = knn.kneighbors(user_preference)
distances1, indices1 = knn.kneighbors(user_preference1)
distances2, indices2 = knn.kneighbors(user_preference2)

# Print User 1,2,3 에 대한 정보..를 함 출력해봄
print("유저 0에 대한 KNN:")
for index_array in indices:
    for index in index_array:
        print(f"{labels[index]}, ", end="")
    print("")
print("유저 1에 대한 KNN:")
for index_array in indices1:
    for index in index_array:
        print(f"{labels[index]}, ", end="")
    print("")
print("유저 2에 대한 KNN:")
for index_array in indices2:
    for index in index_array:
        print(f"{labels[index]}, ", end="")
    print("")

# 각 유저에 대한 거리를 받아오기
# 0,1,2 번 유저의 k 거리에 따른 값들
distances_from_user_preference = distances[0]
distances_from_user_preference1 = distances1[0]
distances_from_user_preference2 = distances2[0]

# 확률로 만들어 보리깅
# 0번 유저에 대한 확률
inverse_distances = 1 / (distances_from_user_preference + 1e-10)
probabilities = inverse_distances / sum(inverse_distances)
# 1번 유저에 대한 확률
inverse_distances1 = 1 / (distances_from_user_preference1 + 1e-10)
probabilities1 = inverse_distances1 / sum(inverse_distances1)
# 2번 유저에 대한 확률
inverse_distances2 = 1 / (distances_from_user_preference2 + 1e-10)
probabilities2 = inverse_distances2 / sum(inverse_distances2)

# --> A(임시) 동네에 사는 User 0,1,2의 선호 음식에 따라 확률(가산으로 더할 점수)를 계산했다.
# --> 나중에 A,B,C 동네 다 이런 식으로 임시 데이터를 추가하면 좋을 듯한데,,, ㅇㄴㅁㄹ ㅏㄴ허ㅣㅏㄴㅇ로하ㅣ ㅁ런

# Step 1: KNN 통합.
# 기본 데이터 셋을 준비
knn_results = {
    "Rice Bowl": [],
    "Famine Crops": [],
    'Electric Grilled Whole Chicken': [],
    'Skewer': [],
    'Takoyaki': [],
    'Taco & Kebab': [],
    'Korean Street Food': [],
    "Bread": [],
    "Cafe & Dessert": [],
    'Etc': []
}
# 저장된 이름에 따른 아이템의 점수를 knn_result랑 매핑 시킨다.
# 새롭게 저장된 값을 재계산해준다.
# 이 코드는 여러 하나의 속성이 여러 개의 값을 추천을 해주는 형태라면 평균 값으로 하나의 값으로 저장한다.
# 예를 들어 "Bread": [0.36, 0.17, 0.17, 0.17] 이면 -> 다 더하고 4로 나누면 0.22값으로 저장되게
# combined_scores = {item: sum(probabilities) / len(probabilities) for item, probabilities in knn_results.items()}

# KNN으로 뽑힌 배열과 knn_result랑 매핑 시킨다.
for i, index_array in enumerate(indices):
    for j, index in enumerate(index_array):
        label = labels[index]  # 매핑
        if label in knn_results:
            knn_results[label].append(probabilities[j]) # 속성 추가한다.
for i, index_array in enumerate(indices1):
    for j, index in enumerate(index_array):
        label = labels[index]  # 매핑
        if label in knn_results:
            knn_results[label].append(probabilities1[j]) # 속성 추가한다.
for i, index_array in enumerate(indices2):
    for j, index in enumerate(index_array):
        label = labels[index]  # 매핑
        if label in knn_results:
            knn_results[label].append(probabilities2[j]) # 속성 추가한다.



# knn에 추가되지 않은 값은 0.01로 바꾼다.
for key in knn_results:
    if not knn_results[key]:  # 키 값이 없을 때 0.01로 저장
        knn_results[key] = [0.01]  # Assign 디폴트 0.01 Assign시킨다.

combined_scores = {item: sum(scores) / len(scores) if scores else 0.01 for item, scores in knn_results.items()}

print("Combined KNN Scores for Each Food Item:")
for item, score in combined_scores.items():
    print(f"{item}: {score:.2f}")

# Step 2: 시계열 분석에 대한 각각의 동네 데이터
# 배열에 동네들 다 담고

# 시계열 계산 클래스에 계산 후에 따로 CSV 파일로 작업하였다. 이 파일에선 계산이 완료된 CSV 파일을 가져온다.
    # 어뜨케 해서 저장했냐면~
    # 시계열 분석을 통한 다음 날의 데이터를 가져오고,
    # 계산된 데이터를 따라, 점수를 계산한다.
# scv에서 해당 동네에 대한 하차 데이터에 따른 값을 가져온다. 가장 많은 값은 1이고 나머진 그 값에 따른 비율 값이 찍힌다.
# neighborhood_score = 0.85
csv_df = pd.read_csv(r'C:\Users\SSAFY\Desktop\bigdata\flowPeople\flowPeople2.csv') # csv 파일을 저장했다.

data_array_flow_town = csv_df[['동', '상대값']].to_numpy()

print(data_array_flow_town)

# 점주가 판매하는 상품이 만약에 Takoyaki라면

food_truck_manager_item = 'Taco & Kebab'
#10개의 점수를 담는 배열 추가 .
food_truck_manager_item_scores = {}

for row in data_array_flow_town:
    same, relative_value = row[0], float(row[1])
    final_scores = {item: score * relative_value for item, score in combined_scores.items()}
    # 점주가 파는 음식과 같은 값이 있다면 저장한다.
    if food_truck_manager_item in final_scores:
        food_truck_manager_item_scores[same] = final_scores[food_truck_manager_item]

# 동네에 담긴 리스트를 sorted 시켜준다.
top_10_neighborhoods = sorted(food_truck_manager_item_scores.items(), key=lambda x: x[1], reverse=True)[:10]

# 타코야끼 점주의 추천 동네를 띄워준다.
print(f"\n  {food_truck_manager_item}를 선호하는 10개의 동네를 추천 :")
for same, score in top_10_neighborhoods:
    print(f"{same}: {score:.8f}")

# 점주가 판매하는 상품이 만약에 Takoyaki라면 타코야키에 대한 값들 중 가장 높은 score을 가진 10개의 동네를 리스트에 담아서 출력


##########################TESET
##########################TESET
##########################TESET
# food_truck_manager_item_scores_test = {}
#
# food_truck_manager_item = 'Taco & Kebab'
#
# for row in data_array_flow_town:
#     same, relative_value = row[0], float(row[1])  # Extract 'same' and 'relative value' for each row
#     relative_value = relative_value*0.01
#     final_scores = {item: score * relative_value for item, score in combined_scores.items()}
#
#     # Sort the final scores in descending order
#     sorted_final_scores = sorted(final_scores.items(), key=lambda item: item[1], reverse=True)
#     if food_truck_manager_item in final_scores:
#         # Store the score for the specific item ('Taco & Kebab') for this neighborhood
#         food_truck_manager_item_scores_test[same] = final_scores[food_truck_manager_item]
#     # Print the sorted final scores for this 'same'
#     print(f"\nFinal scores for {same}:")
#     for item, score in sorted_final_scores:
#         print(f"{item}: {score:.8f}")
#
#     # Assuming food_truck_manager_item_scores_test has been populated as shown in the previous step
#     print(f"\nScores for {food_truck_manager_item} in each neighborhood:")
#
#     # Sort the scores in descending order before printing
#     sorted_scores = sorted(food_truck_manager_item_scores_test.items(), key=lambda item: item[1], reverse=True)
#
#     for same, score in sorted_scores:
#         print(f"{same}: {score:.8f}")

##########################TESET
##########################TESET
##########################TESET